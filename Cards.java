package original.ver2;

/**
 * Cards�N���X
 * @author nakamura
 * �J�[�h�Q�[���p�̃J�[�h��\���B
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
     * �R���X�g���N�^
     * �f�b�L�����������g�����v1�Z�b�g�𐶐��B
     */
    public Cards() {

        restOfCards = SUIT * RANK;
        deckArray = new int[restOfCards];

        for (int i = 0; i < restOfCards; i++) {
            deckArray[i] = i + 1;
        }

    }

    /**
     * getRestOfCards���\�b�h
     * @return restOfCards �J�[�h�̎c�薇��
     */
    int getRestOfCards() {

        return restOfCards;

    }

    /**
     * getRandCard���\�b�h
     * �g�����v�̃Z�b�g���烉���_����1���J�[�h��I�ԁB
     * @return randCard �����_���ɑI�΂ꂽ�J�[�h1��
     */
    int getRandCard() {

        int randNum = (int) Math.floor(Math.random() * restOfCards);
        int randCard = deckArray[randNum];
        deckArray[randNum] = deckArray[--restOfCards];

        return randCard;

    }

    /**
     * getRank���\�b�h
     * 1�`52�̃J�[�h�ԍ�����J�[�h�̐��������߂�B
     * @param cardNum �J�[�h�ԍ�
     * @return �J�[�h�̐���
     */
    int getRank(int cardNum) {

        return (cardNum - 1) % RANK + 1;

    }

    /**
     * getSuit���\�b�h
     * 1�`52�̃J�[�h�ԍ�����J�[�h�̃X�[�g�����߂�B
     * @param cardNum �J�[�h�ԍ�
     * @return suitStr �X�[�g
     */
    String getSuit(int cardNum) {

        String suitStr = "";

        switch ((cardNum - 1) / RANK) {
        case SPADE:
            suitStr = "�X�y�[�h";
            break;
        case DIAMOND:
            suitStr = "�_�C��";
            break;
        case CLUB:
            suitStr = "�N���u";
            break;
        case HEART:
            suitStr = "�n�[�g";
            break;
        default:
            suitStr = "";
        }

        return suitStr;

    }

}
