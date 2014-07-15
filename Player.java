package original.ver1;

/**
 * Player�N���X
 * @author r3pc
 * �J�[�h�Q�[���̃v���C���[��\���B
 */
public class Player {

    private int[] handsArray;
    private int numOfHands;

    /**
     * �R���X�g���N�^
     * @param num ���Ă��D�̖���
     */
    public Player(int num) {

        numOfHands = 0;
        handsArray = new int[num];

    }

    /**
     * getHandsArray���\�b�h
     * ��D�z����擾����B
     * @return handsArray ��D�z��
     */
    protected int[] getHandsArray() {

        return handsArray;

    }

    protected int getNumOfHands() {

        return numOfHands;

    }

    /**
     * pickCards���\�b�h
     * �����_���ɑI�񂾃J�[�h���w�薇����������D�ɉ�����B
     * @param cards �g�����v�̃Z�b�g
     * @param numOfCards �J�[�h�̖���
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
     * pullOutHands���\�b�h
     * ��D��1���̂Ă�B
     * @param position �̂Ă�J�[�h�̈ʒu
     * @return selectCard �I�񂾃J�[�h
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
     * getRankArray���\�b�h
     * �w�肳�ꂽ�������̎�D�̐�����Ԃ��B
     * @param cards �g�����v�̃Z�b�g
     * @param numOfCards �J�[�h�̖���
     * @return rankArray ��D�̐����̔z��
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
     * getSuitArray���\�b�h
     * �w�肳�ꂽ�������̎�D�̃X�[�g��Ԃ��B
     * @param cards �g�����v�̃Z�b�g
     * @param numOfCards �J�[�h�̖���
     * @return suitArray ��D�̃X�[�g�̔z��
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
     * showHands���\�b�h
     * ��D���w�薇���������\������B
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
