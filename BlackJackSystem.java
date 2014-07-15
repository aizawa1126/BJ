package original.ver2;

import lib.Input;

/**
 * BlackJackSystem�N���X
 * @author nakamura
 * �u���b�N�W���b�N�����邽�߂̃N���X�B
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

    private static int result = 0;

    /**
     * @param args �R�}���h���C������
     */
    public static void main(String[] args) {

        String ansStr = "";

        dispBanner();

         do {
             tryBlackJack();

             ansStr = Input.getString("�����܂��� (y/n)");
             ansStr = convert(ansStr);
         } while(ansStr != null && !ansStr.equals("n"));

    }

    /**
     * dispBanner���\�b�h
     * �Q�[���̃o�i�[��\������B
     */
    private static void dispBanner() {

        System.out.println("\n�������������������� Blackjack ����������������\n");

    }

    /**
     * getTotalPoint���\�b�h
     * ��D�̍��v�|�C���g���v�Z����B
     * @param someone �v���[���[
     * @return totalPoint ���v�|�C���g
     */
    private static int getTotalPoint(Player someone) {

        int totalPoint = 0;
        int numOfHands = someone.getNumOfHands();
        int[] handsOfSomeone = someone.getHandsArray();

        for (int i = 0; i < numOfHands; i++) {
            totalPoint += toPoint(cards.getRank(handsOfSomeone[i]));
        }

        return totalPoint;

    }

    /**
     * toPoint���\�b�h
     * �J�[�h�̐������|�C���g�ɕϊ�����B
     * @param rank �J�[�h�̐���
     * @return point �|�C���g
     */
    private static int toPoint(int rank) {

         int point = (rank < Cards.JACK ? rank : MAX_POINT);
         return point;

    }


    /**
     * animation���\�b�h
     * ��0.1�b��1������\������B
     */
    private static void animation() {

        System.out.println();
        int numOfTimes = ANIMATION_SPEED / 10;

        for (int i = 0; i < numOfTimes; i++) {
            System.out.print("����");
            delay(ANIMATION_SPEED);
        }
        System.out.println();

    }

    /**
     * delay���\�b�h
     * @param time �x�~�������~���b��
     * �~���b�P�ʂŋx�~����B
     */
    public static void delay(int time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * judge���\�b�h
     * ���v�|�C���g���r���A���s��Ԃ��B
     * @return result ���s�̌���
     */
    private static int judge() {

        int playerTotalPoint = getTotalPoint(player);
        int dealerTotalPoint = getTotalPoint(dealer);

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
     * hit���\�b�h
     * �v���[���[����D�������B
     */
    private static void hit() {

        String ansStr = "";

        while (getTotalPoint(player) < BLACKJACK) {
            ansStr = Input.getString("�J�[�h�������܂��� (y/n)");
            ansStr = convert(ansStr);
            if (ansStr != null && ansStr.equals("n")) {
                break;
            }
            player.pickCards(cards, 1);
            System.out.println();
            show();
        }

    }

    /**
     * deal���\�b�h
     * ���v�|�C���g��16��������܂Ńf�B�[���͎�D�������B
     */
    private static void deal() {

        while (getTotalPoint(dealer) < BLACKJACK - COM_HANDICAP) {
            dealer.pickCards(cards, 1);
        }

    }

    /**
     * convert���\�b�h
     * ������̃A���t�@�x�b�g�𔼊p�����ɕϊ�����B
     * �ihttp://www.ra13.org/java/TwoByteToOneByte.html���j
     * @param str ������
     * @return sb.toString() �ϊ���̔��p������
     */
    public static String convert(String str) {
        char[] cc = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cc) {
            char newChar = c;
            if ((('�`' <= c) && (c <= '�y')) || (('��' <= c) && (c <= '��'))) {
                newChar = (char) (c - ('�`' - 'A'));
            }
            sb.append(newChar);
        }
        return sb.toString();
    }

    /**
     * tryBlackjack���\�b�h
     * �u���b�N�W���b�N���ЂƏ����s���B
     */
    private static void tryBlackJack() {

        player = new Player(NUMBER_OF_HANDS);
        dealer = new Player(NUMBER_OF_HANDS);
        result = 0;

        if (cards.getRestOfCards() <= NUMBER_OF_HANDS * 2) {
            cards = new Cards();
        }

        player.pickCards(cards, 2);
        dealer.pickCards(cards, 2);

        show();
        hit();
        deal();

        animation();
        result = judge();
        show();

    }

    /**
     * dispPayerAndDealerHands���\�b�h
     * �v���[���[�ƃf�B�[���[�̎��\������B
     * @param result ���s����
     */
    private static void dispPlayerAndDealerHands() {

        String playerStr = "��Player\t(" + Integer.toString(getTotalPoint(player)) + "P)\t";
        String dealerStr = "";

        if (result == 0) {

            dealerStr = "��Computer\t(--P)\t";

            System.out.print("\n" + playerStr);
            player.showHands(cards, NUMBER_OF_HANDS);
            System.out.print(dealerStr);
            dealer.showHands(cards, 1);
            System.out.print("\n");

        } else {

            dealerStr = "��Computer\t(" + Integer.toString(getTotalPoint(dealer)) + "P)\t";

            System.out.print("\n" + playerStr);
            player.showHands(cards, NUMBER_OF_HANDS);
            System.out.print(dealerStr);
            dealer.showHands(cards, NUMBER_OF_HANDS);

        }

    }

    /**
     * show���\�b�h
     * �Q�[���̏󋵂�\������B
     * @param result ���s����
     */
    private static void show() {

        dispPlayerAndDealerHands();

        if (result != 0) {

            String resultStr = "";

            switch (result) {
            case PLAYER_WIN:
                resultStr = "���Ȃ��̏���";
                break;
            case DEALER_WIN:
                resultStr = "�R���s���[�^�̏���";
                break;
            case DRAW:
                resultStr = "��������";
                break;
            default:
                resultStr = "";
            }

            System.out.println("\n" + resultStr + "\n");

        }

    }

}
