package original.ver2;

/**
 * Cardsクラス
 * @author nakamura
 * カードゲーム用のカードを表す。
 */
public class Cards {

    static final int SUIT = 4;
    static final int RANK = 13;

    static final int SPADE = 0;
    static final int DIAMOND = 1;
    static final int CLUB = 2;
    static final int HEART = 3;

    static final int KING = 13;
    static final int QUEEN = 12;
    static final int JACK = 11;

    private int[] deckArray;
    private int restOfCards;

    /**
     * コンストラクタ
     * デッキを初期化しトランプ1セットを生成。
     */
    public Cards() {

        restOfCards = SUIT * RANK;
        deckArray = new int[restOfCards];

        for (int i = 0; i < restOfCards; i++) {
            deckArray[i] = i + 1;
        }

    }

    /**
     * getRestOfCardsメソッド
     * @return restOfCards カードの残り枚数
     */
    int getRestOfCards() {

        return restOfCards;

    }

    /**
     * getRandCardメソッド
     * トランプのセットからランダムに1枚カードを選ぶ。
     * @return randCard ランダムに選ばれたカード1枚
     */
    int getRandCard() {

        int randNum = (int) Math.floor(Math.random() * restOfCards);
        int randCard = deckArray[randNum];
        deckArray[randNum] = deckArray[--restOfCards];

        return randCard;

    }

    /**
     * getRankメソッド
     * 1～52のカード番号からカードの数字を求める。
     * @param cardNum カード番号
     * @return カードの数字
     */
    int getRank(int cardNum) {

        return (cardNum - 1) % RANK + 1;

    }

    /**
     * getSuitメソッド
     * 1～52のカード番号からカードのスートを求める。
     * @param cardNum カード番号
     * @return suitStr スート
     */
    String getSuit(int cardNum) {

        String suitStr = "";

        switch ((cardNum - 1) / RANK) {
        case SPADE:
            suitStr = "スペード";
            break;
        case DIAMOND:
            suitStr = "ダイヤ";
            break;
        case CLUB:
            suitStr = "クラブ";
            break;
        case HEART:
            suitStr = "ハート";
            break;
        default:
            suitStr = "";
        }

        return suitStr;

    }

}
