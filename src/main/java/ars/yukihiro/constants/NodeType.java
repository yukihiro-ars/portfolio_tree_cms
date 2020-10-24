package ars.yukihiro.constants;

/**
 * ノードタイプ
 * @author yukihiro adachi
 */
public enum NodeType {

        INNER(1, "INNER"),
        LEAF(2, "LEAF");

        // ノードタイプ
        private int nodeType;
        // ノードタイプ名
        private String nodeTypeNm;

        /**
         * ノードタイプのコンストラクタ.
         * @param t ノードタイプ
         * @param nm ノードタイプ名
         */
        private NodeType(int t, String nm) {
                nodeType = t;
                nodeTypeNm = nm;
        }

        /**
         * ノードタイプの値を返却する.
         * @return ノードタイプ
         */
        public int getValue() {
                return nodeType;
        }


        /**
         * 定数の名前を返却する.
         * @return
         */
        @Override
        public String toString() {
                return nodeTypeNm;
        }
}
