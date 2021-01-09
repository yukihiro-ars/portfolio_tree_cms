package ars.yukihiro.constants;

import java.util.Objects;

/**
 * ノードタイプ列挙型.
 * @author yukihiro adachi
 */
public enum NodeType {

        INNER(1, "INNER"),
        LEAF(2, "LEAF");

        // 値
        private int value;
        // ラベル
        private String label;

        /**
         * ノードタイプのコンストラクタ.
         * @param v 値
         * @param l ラベル
         */
        private NodeType(int v, String l) {
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


        /**
         * 数値から列挙型に変換する.
         * @param value
         * @return
         */
        public static NodeType convertByValue(Integer value) {
                for (NodeType n : NodeType.values()) {
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
