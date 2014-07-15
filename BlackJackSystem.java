package original.ver1;

import lib.Input;

/**
 * BlackJackSystemクラス
 * @author r3pc
 * ブラックジャックをするためのクラス。
 */
public class BlackJackSystem {

    static final int NUMBER_OF_HANDS = 8;
    static final int MAX_POINT = 10;
    static final int BLACKJACK = 21;
    static final int COM_HANDICAP = 5;
    static final int ANIMATION_SPEED = 100;

    static final int PLAYER_WIN = 1;
    static final int DEALER_WIN = 2;
    static final int DRAW = 3;

    private static Cards cards = new Cards();
    private static Player player;
    private static Player dealer;

    /**
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {

        String ansStr = "";

        dispBanner();

         do {
             tryBlackJack();

             ansStr = Input.getString("続けますか (y/n)");
             ansStr = convert(ansStr);
         } while(ansStr != null && !ansStr.equals("n"));

    }

    /**
     * dispBannerメソッド
     * ゲームのバナーを表示する。
     */
    private static void dispBanner() {

        System.out.println("\n■□■□■□■□■□ Blackjack ■□■□■□■□\n");

    }

    /**
     * getTotalPointメソッド
     * 手札の合計ポイントを計算する。
     * @param someone プレーヤー
     * @return totalPoint 合計ポイント
     */
    private static int getTotalPoint(Player someone) {

        int numOfHands = someone.getNumOfHands();
        int[] rankArray = someone.getRankArray(cards, numOfHands);
        int totalPoint = 0;

        for (int i = 0; i < numOfHands; i++) {
            totalPoint += toPoint(rankArray[i]);
        }

        return totalPoint;

    }

    /**
     * toPointメソッド
     * カードの数字をポイントに変換する。
     * @param rank カードの数字
     * @return point ポイント
     */
    private static int toPoint(int rank) {

         int point = (rank < Cards.JACK ? rank : MAX_POINT);
         return point;

    }

    /**
     * dispHandsメソッド
     * 手札を表示する。
     */
    private static void dispHands() {

        String playerStr = "■Player\t(" + Integer.toString(getTotalPoint(player)) + "P)\t";
        String dealerStr = "□Computer\t(--P)\t";

        System.out.print("\n" + playerStr);
        player.showHands(cards, NUMBER_OF_HANDS);
        System.out.print(dealerStr);
        dealer.showHands(cards, 1);
        System.out.println();

    }

    /**
     * animationメソッド
     * 約0.1秒に1つ☆☆を表示する。
     */
    public static void animation() {

        System.out.println();
        int numOfTimes = ANIMATION_SPEED / 10;

        for (int i = 0; i < numOfTimes; i++) {
            System.out.print("☆☆");
            delay(ANIMATION_SPEED);
        }
        System.out.println();

    }

    /**
     * delayメソッド
     * @param time 休止したいミリ秒数
     * ミリ秒単位で休止する。
     */
    public static void delay(int time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * judgeメソッド
     * 合計ポイントを比較し、勝敗を返す。
     * @return result 勝敗の結果
     */
    private static int judge(int playerTotalPoint, int dealerTotalPoint) {

        int result = 0;

        if ((dealerTotalPoint > BLACKJACK && playerTotalPoint > BLACKJACK)
                || dealerTotalPoint == playerTotalPoint) {
            result = DRAW;
        } else if (playerTotalPoint <= BLACKJACK && (dealerTotalPoint > BLACKJACK
                || dealerTotalPoint < playerTotalPoint)) {
            result = PLAYER_WIN;
        } else {
            result = DEALER_WIN;
        }

        return result;

    }

    /**
     * dispResultメソッド
     * 勝敗結果を受け取って表示する。
     * @param result 勝敗結果
     */
    private static void dispResult(int result) {

        String resultStr = "";

        switch (result) {
        case PLAYER_WIN:
            resultStr = "あなたの勝ち";
            break;
        case DEALER_WIN:
            resultStr = "コンピュータの勝ち";
            break;
        case DRAW:
            resultStr = "引き分け";
            break;
        default:
            resultStr = "";
        }

        String playerStr = "■Player\t(" + Integer.toString(getTotalPoint(player)) + "P)\t";
        String dealerStr = "□Computer\t(" + Integer.toString(getTotalPoint(dealer)) + "P)\t";

        System.out.print("\n" + playerStr);
        player.showHands(cards, NUMBER_OF_HANDS);
        System.out.print(dealerStr);
        dealer.showHands(cards, NUMBER_OF_HANDS);

        System.out.println("\n" + resultStr + "\n");

    }

    /**
     * hitメソッド
     * プレーヤーが手札を引く。
     */
    private static void hit() {

        String ansStr = "";

        while (getTotalPoint(player) < BLACKJACK) {
            ansStr = Input.getString("カードを引きますか (y/n)");
            ansStr = convert(ansStr);
            if (ansStr != null && ansStr.equals("n")) {
                break;
            }
            player.pickCards(cards, 1);
            System.out.println();
            dispHands();
        }

    }

    /**
     * dealメソッド
     * 合計ポイントが16をこえるまでディーラは手札を引く。
     */
    private static void deal() {

        while (getTotalPoint(dealer) < BLACKJACK - COM_HANDICAP) {
            dealer.pickCards(cards, 1);
        }

    }

    /**
     * convertメソッド
     * 文字列のアルファベットを半角文字に変換する。
     * （http://www.ra13.org/java/TwoByteToOneByte.htmlより）
     * @param str 文字列
     * @return sb.toString() 変換後の半角文字列
     */
    public static String convert(String str) {
        char[] cc = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cc) {
            char newChar = c;
            if ((('Ａ' <= c) && (c <= 'Ｚ')) || (('ａ' <= c) && (c <= 'ｚ'))) {
                newChar = (char) (c - ('Ａ' - 'A'));
            }
            sb.append(newChar);
        }
        return sb.toString();
    }

    /**
     * tryBlackjackメソッド
     * ブラックジャックをひと勝負行う。
     */
    public static void tryBlackJack() {
    	
    	player = new Player(NUMBER_OF_HANDS);
        dealer = new Player(NUMBER_OF_HANDS);
    	
    	if (cards.getRestOfCards() <= NUMBER_OF_HANDS * 2) {
            cards = new Cards();
        }

        player.pickCards(cards, 2);
        dealer.pickCards(cards, 2);

        dispHands();
        hit();
        deal();

        animation();
        dispResult(judge(getTotalPoint(player), getTotalPoint(dealer)));
    	
    }

}
