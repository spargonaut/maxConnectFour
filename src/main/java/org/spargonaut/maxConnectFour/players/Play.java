package org.spargonaut.maxConnectFour.players;

public class Play {
    private int scoreDifference;
    private int column;

    private Play(PlayBuilder playBuilder) {
        this.scoreDifference = playBuilder.scoreDifference;
        this.column = playBuilder.column;
    }

    public int getScoreDifference() {
        return this.scoreDifference;
    }

    public int getColumn() {
        return this.column;
    }

    public void setScoreDifference(int scoreDifference) {
        this.scoreDifference = scoreDifference;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    static class PlayBuilder {
        int scoreDifference = -99999;
        int column = -1;

        public PlayBuilder scoreDifference(int score) {
            this.scoreDifference = score;
            return this;
        }

        public PlayBuilder column(int column) {
            this.column = column;
            return this;
        }

        public Play build() {
            return new Play(this);
        }
    }

}
