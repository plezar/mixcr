/*
 * Copyright (c) 2014-2015, Bolotin Dmitry, Chudakov Dmitry, Shugay Mikhail
 * (here and after addressed as Inventors)
 * All Rights Reserved
 *
 * Permission to use, copy, modify and distribute any part of this program for
 * educational, research and non-profit purposes, by non-profit institutions
 * only, without fee, and without a written agreement is hereby granted,
 * provided that the above copyright notice, this paragraph and the following
 * three paragraphs appear in all copies.
 *
 * Those desiring to incorporate this work into commercial products or use for
 * commercial purposes should contact the Inventors using one of the following
 * email addresses: chudakovdm@mail.ru, chudakovdm@gmail.com
 *
 * IN NO EVENT SHALL THE INVENTORS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE, EVEN IF THE INVENTORS HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * THE SOFTWARE PROVIDED HEREIN IS ON AN "AS IS" BASIS, AND THE INVENTORS HAS
 * NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR
 * MODIFICATIONS. THE INVENTORS MAKES NO REPRESENTATIONS AND EXTENDS NO
 * WARRANTIES OF ANY KIND, EITHER IMPLIED OR EXPRESS, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY OR FITNESS FOR A
 * PARTICULAR PURPOSE, OR THAT THE USE OF THE SOFTWARE WILL NOT INFRINGE ANY
 * PATENT, TRADEMARK OR OTHER RIGHTS.
 */
package com.milaboratory.mixcr.vdjaligners;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.milaboratory.core.alignment.kaligner1.KAlignerParameters;
import com.milaboratory.mixcr.reference.GeneFeature;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE)
public final class KGeneAlignmentParameters extends GeneAlignmentParameters<KGeneAlignmentParameters>
        implements java.io.Serializable{
    private KAlignerParameters parameters;

    @JsonCreator
    public KGeneAlignmentParameters(
            @JsonProperty("geneFeatureToAlign") GeneFeature geneFeatureToAlign,
            @JsonProperty("parameters") KAlignerParameters parameters) {
        super(geneFeatureToAlign);
        this.parameters = parameters;
    }

    public KAlignerParameters getParameters() {
        return parameters;
    }

    public KGeneAlignmentParameters setParameters(KAlignerParameters parameters) {
        this.parameters = parameters;
        return this;
    }

    @Override
    public KGeneAlignmentParameters clone() {
        return new KGeneAlignmentParameters(geneFeatureToAlign, parameters.clone());
    }

    @Override
    public String toString() {
        return "KGeneAlignmentParameters{" +
                "parameters=" + parameters +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KGeneAlignmentParameters)) return false;
        if (!super.equals(o)) return false;

        KGeneAlignmentParameters that = (KGeneAlignmentParameters) o;

        if (!parameters.equals(that.parameters)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + parameters.hashCode();
        return result;
    }
}
