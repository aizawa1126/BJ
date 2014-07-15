package original.ver2;

/**
 * Playerクラス
 * @author nakamura
 * カードゲームのプレイヤーを表す。
 */
public class Player {

    private int[] handsArray;
    private int numOfHands;

    /**
     * コンストラクタ
     * @param maxNumOfHands 持てる手札の枚数
     */
    public Player(int maxNumOfHands) {

        this.numOfHands = 0;
        this.handsArray = new int[maxNumOfHands];

    }

    /**
     * getNumOfHandsメソッド
     * 有効な手札の枚数を取得する。
     * @return numOfHands 有効な手札の枚数
     */
    int getNumOfHands() {

        return numOfHands;

    }

    /**
     * getHandsArrayメソッド
     * 手札配列のコピーを取得する。
     * @return handsCopyArray 手札配列のコピー
     */
    int[] getHandsArray() {

        int arrayLength = handsArray.length;
        int[] handsCopyArray = new int[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            handsCopyArray[i] = handsArray[i];
        }

        return handsCopyArray;

    }

    /**
     * pickCardsメソッド
     * ランダムに選んだカードを指定枚数分だけ手札に加える。
     * @param cards トランプのセット
     * @param numOfCards カードの枚数
     */
    void pickCards(Cards cards, int numOfCards) {

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
    int pullOutHands(int position) {

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
     * showHandsメソッド
     * 手札を指定枚数分だけ表示する。
     * @param cards
     */
    void showHands(Cards cards, int numOfCards) {

        if (numOfCards > numOfHands) {
            numOfCards = numOfHands;
        }

        String dataStr = "";

        for (int i = 0; i < numOfCards; i++) {
            dataStr += cards.getSuit(handsArray[i]) + "<"
                    + Integer.toString(cards.getRank(handsArray[i])) + ">\t";
        }

        if (numOfCards < numOfHands) {
            for (int i = numOfCards; i < numOfHands; i++) {
                dataStr += "********<->\t";
            }
        }

        System.out.print(dataStr + "\n");

    }




}
