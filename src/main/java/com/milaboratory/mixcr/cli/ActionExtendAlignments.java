package com.milaboratory.mixcr.cli;

import cc.redberry.pipe.CUtils;
import cc.redberry.pipe.blocks.ParallelProcessor;
import cc.redberry.pipe.util.Indexer;
import cc.redberry.pipe.util.OrderedOutputPort;
import com.beust.jcommander.Parameter;
import com.milaboratory.cli.Action;
import com.milaboratory.cli.ActionHelper;
import com.milaboratory.cli.ActionParameters;
import com.milaboratory.cli.ActionParametersWithOutput;
import com.milaboratory.mixcr.basictypes.VDJCAlignments;
import com.milaboratory.mixcr.basictypes.VDJCAlignmentsReader;
import com.milaboratory.mixcr.basictypes.VDJCAlignmentsWriter;
import com.milaboratory.mixcr.util.AlignmentExtender;
import com.milaboratory.util.SmartProgressReporter;
import io.repseq.core.Chains;
import io.repseq.core.ReferencePoint;

import java.util.List;

/**
 * @author Stanislav Poslavsky
 */
public class ActionExtendAlignments implements Action {
    private final ExtendCD3Parameters parameters = new ExtendCD3Parameters();

    @Override
    public void go(ActionHelper helper) throws Exception {
        long time = System.currentTimeMillis();
        try (final VDJCAlignmentsReader reader = new VDJCAlignmentsReader(parameters.getInput());
             final VDJCAlignmentsWriter writer = new VDJCAlignmentsWriter(parameters.getOutput())) {
            SmartProgressReporter.startProgressReport("Processing", reader);

            writer.header(reader.getParameters(), reader.getUsedGenes());
            AlignmentExtender extender = new AlignmentExtender(parameters.getChains(), parameters.extensionQuality,
                    reader.getParameters().getVAlignerParameters().getParameters().getScoring(),
                    reader.getParameters().getJAlignerParameters().getParameters().getScoring(),
                    ReferencePoint.parse(parameters.vAnchorPoint),
                    ReferencePoint.parse(parameters.jAnchorPoint));

            ParallelProcessor<VDJCAlignments, VDJCAlignments> pp = new ParallelProcessor<>(reader, extender, 2);
            for (VDJCAlignments alignments : CUtils.it(new OrderedOutputPort<>(pp,
                    new Indexer<VDJCAlignments>() {
                        @Override
                        public long getIndex(VDJCAlignments o) {
                            return o.getAlignmentsIndex();
                        }
                    })))
                writer.write(alignments);
            writer.setNumberOfProcessedReads(reader.getNumberOfReads());

            time = System.currentTimeMillis() - time;

            // Writing report to stout
            System.out.println("============= Report ==============");
            Util.writeReportToStdout(time, extender);
            if (parameters.report != null)
                Util.writeReport(parameters.getInput(), parameters.getOutput(),
                        helper.getCommandLineArguments(), parameters.report, time, extender);
        }
    }

    @Override
    public String command() {
        return "extendAlignments";
    }

    @Override
    public ActionParameters params() {
        return parameters;
    }

    private static final class ExtendCD3Parameters extends ActionParametersWithOutput {
        @Parameter(description = "input.vdjca[.gz] output.vdjca[.gz]")
        public List<String> parameters;

        @Parameter(description = "Apply procedure only to alignments with specific immunological-receptor chains.",
                names = {"-c", "--chains"})
        public String chains = "TCR";

        @Parameter(description = "Report file.",
                names = {"-r", "--report"})
        public String report;

        @Parameter(description = "Quality score of extended sequence.",
                names = {"-q", "--quality"})
        public byte extensionQuality = 30;

        @Parameter(description = "V extension anchor point",
                names = {"--v-anchor"})
        public String vAnchorPoint = "CDR3Begin";

        @Parameter(description = "J extension anchor point",
                names = {"--j-anchor"})
        public String jAnchorPoint = "CDR3End";

        private String getInput() {
            return parameters.get(0);
        }

        private String getOutput() {
            return parameters.get(1);
        }

        public Chains getChains() {
            return Util.parseLoci(chains);
        }

        @Override
        protected List<String> getOutputFiles() {
            return parameters.subList(1, parameters.size());
        }
    }
}
