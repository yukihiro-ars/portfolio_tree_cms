package ars.yukihiro.portfolio.tree.cms.enums;

/**
 * ツリーモード列挙型.
 * @author yukihiro adachi
 */
public enum TreeMode {

        VIEW(1, "表示"),
        MOVE(2, "移動"),
        ADD(3, "追加"),
        UPDATE(4, "更新"),
        DELETE(5, "削除");

        // 値
        private int value;
        // ラベル
        private String label;

        /**
         * ノードタイプのコンストラクタ.
         * @param v 値
         * @param l ラベル
         */
        private TreeMode(int v, String l) {
                value = v;
                label = l;
        }

        /**
         * ノードタイプの値を返却する.
         * @return ノードタイプ
         */
        public int getValue() {
                return value;
        }

        /**
         * ノードタイプ名を返却する.
         * @return ノードタイプ名
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


        public static TreeMode convertByValue(String value) {
                return convertByValue(Integer.parseInt(value));
        }
        /**
         * 数値から列挙型に変換する.
         * @param value
         * @return
         */
        public static TreeMode convertByValue(Integer value) {
                for (TreeMode n : TreeMode.values()) {
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
