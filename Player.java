package original.ver1;

/**
 * Playerクラス
 * @author r3pc
 * カードゲームのプレイヤーを表す。
 */
public class Player {

    private int[] handsArray;
    private int numOfHands;

    /**
     * コンストラクタ
     * @param num 持てる手札の枚数
     */
    public Player(int num) {

        numOfHands = 0;
        handsArray = new int[num];

    }

    /**
     * getHandsArrayメソッド
     * 手札配列を取得する。
     * @return handsArray 手札配列
     */
    protected int[] getHandsArray() {

        return handsArray;

    }

    protected int getNumOfHands() {

        return numOfHands;

    }

    /**
     * pickCardsメソッド
     * ランダムに選んだカードを指定枚数分だけ手札に加える。
     * @param cards トランプのセット
     * @param numOfCards カードの枚数
     */
    protected void pickCards(Cards cards, int numOfCards) {

        if (handsArray.length < numOfCards) {
            return;
        }

        numOfCards += numOfHands;

        for (int i = numOfHands; i < numOfCards; i++) {
            if (i > handsArray.length) {
                break;
            } else {
                handsArray[i] = cards.getRandCard();
                numOfHands++;
            }
        }

    }

    /**
     * pullOutHandsメソッド
     * 手札を1枚捨てる。
     * @param position 捨てるカードの位置
     * @return selectCard 選んだカード
     */
    protected int pullOutHands(int position) {

        if (position > handsArray.length) {
            position = handsArray.length;
        }

        int selectCard = handsArray[position];

        for (int i = position; i < numOfHands; i++) {
            handsArray[i] = (i != numOfHands - 1 ? handsArray[i + 1] : 0);
        }

        return selectCard;

    }

    /**
     * getRankArrayメソッド
     * 指定された枚数分の手札の数字を返す。
     * @param cards トランプのセット
     * @param numOfCards カードの枚数
     * @return rankArray 手札の数字の配列
     */
    protected int[] getRankArray(Cards cards, int numOfCards) {

        if (numOfHands < numOfCards) {
            numOfCards = numOfHands;
        }

        int[] rankArray = new int[numOfCards];

        for (int i = 0; i < numOfCards; i++) {
            rankArray[i] = cards.getRank(handsArray[i]);
        }

        return rankArray;

    }

    /**
     * getSuitArrayメソッド
     * 指定された枚数分の手札のスートを返す。
     * @param cards トランプのセット
     * @param numOfCards カードの枚数
     * @return suitArray 手札のスートの配列
     */
    protected String[] getSuitArray(Cards cards, int numOfCards) {

        if (numOfHands < numOfCards) {
            numOfCards = numOfHands;
        }

        String[] suitArray = new String[numOfCards];

        for (int i = 0; i < numOfCards; i++) {
            suitArray[i] = cards.getSuit(handsArray[i]);
        }

        return suitArray;

    }

    /**
     * showHandsメソッド
     * 手札を指定枚数分だけ表示する。
     * @param cards
     */
    protected void showHands(Cards cards, int numOfCards) {

        String[] suitArray = getSuitArray(cards, numOfCards);
        int[] rankArray = getRankArray(cards, numOfCards);

        numOfCards = rankArray.length;
        String dataStr = "";

        for (int i = 0; i < numOfCards; i++) {
            dataStr += suitArray[i] + "<" + Integer.toString(rankArray[i]) + ">\t";
        }

        if (numOfCards < numOfHands) {
            for (int i = numOfCards; i < numOfHands; i++) {
                dataStr += "********<->\t";
            }
        }

        System.out.print(dataStr + "\n");

    }


}
