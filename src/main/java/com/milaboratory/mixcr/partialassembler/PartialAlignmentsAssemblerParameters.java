/*
 * Copyright (c) 2014-2016, Bolotin Dmitry, Chudakov Dmitry, Shugay Mikhail
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
package com.milaboratory.mixcr.partialassembler;

import com.milaboratory.mitools.merger.QualityMergingAlgorithm;

public class PartialAlignmentsAssemblerParameters {
    private int kValue, kOffset, minimalOverlap, maxScoreValue;
    QualityMergingAlgorithm qualityMergingAlgorithm;

    public PartialAlignmentsAssemblerParameters(int kValue, int kOffset, int minimalOverlap, int maxScoreValue, QualityMergingAlgorithm qualityMergingAlgorithm) {
        this.kValue = kValue;
        this.kOffset = kOffset;
        this.minimalOverlap = minimalOverlap;
        this.maxScoreValue = maxScoreValue;
        this.qualityMergingAlgorithm = qualityMergingAlgorithm;
    }

    public void setQualityMergingAlgorithm(QualityMergingAlgorithm qualityMergingAlgorithm) {
        this.qualityMergingAlgorithm = qualityMergingAlgorithm;
    }

    public QualityMergingAlgorithm getQualityMergingAlgorithm() {
        return qualityMergingAlgorithm;
    }

    public void setMaxScoreValue(int maxScoreValue) {
        this.maxScoreValue = maxScoreValue;
    }

    public int getMaxScoreValue() {
        return maxScoreValue;
    }

    public int getKValue() {
        return kValue;
    }

    public void setKValue(int kValue) {
        this.kValue = kValue;
    }

    public int getKOffset() {
        return kOffset;
    }

    public void setKOffset(int kOffset) {
        this.kOffset = kOffset;
    }

    public int getMinimalOverlap() {
        return minimalOverlap;
    }

    public void setMinimalOverlap(int minimalOverlap) {
        this.minimalOverlap = minimalOverlap;
    }
}
