package original.ver2;

/**
 * Player�N���X
 * @author nakamura
 * �J�[�h�Q�[���̃v���C���[��\���B
 */
public class Player {

    private int[] handsArray;
    private int numOfHands;

    /**
     * �R���X�g���N�^
     * @param maxNumOfHands ���Ă��D�̖���
     */
    public Player(int maxNumOfHands) {

        this.numOfHands = 0;
        this.handsArray = new int[maxNumOfHands];

    }

    /**
     * getNumOfHands���\�b�h
     * �L���Ȏ�D�̖������擾����B
     * @return numOfHands �L���Ȏ�D�̖���
     */
    int getNumOfHands() {

        return numOfHands;

    }

    /**
     * getHandsArray���\�b�h
     * ��D�z��̃R�s�[���擾����B
     * @return handsCopyArray ��D�z��̃R�s�[
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
     * pickCards���\�b�h
     * �����_���ɑI�񂾃J�[�h���w�薇����������D�ɉ�����B
     * @param cards �g�����v�̃Z�b�g
     * @param numOfCards �J�[�h�̖���
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
     * pullOutHands���\�b�h
     * ��D��1���̂Ă�B
     * @param position �̂Ă�J�[�h�̈ʒu
     * @return selectCard �I�񂾃J�[�h
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
     * showHands���\�b�h
     * ��D���w�薇���������\������B
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
