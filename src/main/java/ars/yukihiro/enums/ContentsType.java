package ars.yukihiro.enums;

/**
 * コンテンツタイプ列挙型.
 * @author yukihiro adachi
 */
public enum ContentsType {

        PLANE(0, "PLANE"),
        CARRIER(1, "CARRIER");

        // 値
        private int value;
        // ラベル
        private String label;

        /**
         * コンテンツタイプのコンストラクタ.
         * @param v 値
         * @param l ラベル
         */
        private ContentsType(int v, String l) {
                value = v;
                label = l;
        }

        /**
         * コンテンツタイプの値を返却する.
         * @return コンテンツタイプ
         */
        public int getValue() {
                return value;
        }

        /**
         * コンテンツタイプ名を返却する.
         * @return コンテンツタイプ名
         */
        public String getLabel() {
                return label;
        }

        /**
         * 名称を取得.
         * @return
         */
        public String getName() {
                return this.name();
        }


        public static ContentsType convertByValue(String value) {
                return convertByValue(Integer.parseInt(value));
        }
        /**
         * 数値から列挙型に変換する.
         * @param value
         * @return
         */
        public static ContentsType convertByValue(Integer value) {
                for (ContentsType n : ContentsType.values()) {
                        if (n.getValue() == value) {
                                return n;
                        }
                }
                throw new IllegalArgumentException();
        }

        /**
         * オブジェクト識別用の文字列を返却する.
         * @return
         */
        @Override
        public String toString() {
                return label + "(" + String.valueOf(value) + ")";
        }
}
