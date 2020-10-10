DROP TABLE IF EXISTS business.NODE;
CREATE TABLE business.NODE(
    NODE_ID char(10) NOT NULL,
    NODE_TYPE char(1) NOT NULL DEFAULT 1,
    HIERARCHY smallint NOT NULL DEFAULT 1,
    NODE_NM_LGC varchar(20) NOT NULL,
    NODE_NM_PSC varchar(20) NOT NULL,
    CONTENTS_ID char(10),
    UP_DT char(17),
    UP_NM varchar(20),
    RG_DT char(17),
    RG_NM varchar(20),
    CONSTRAINT pk_NODE PRIMARY KEY (NODE_ID)
);
COMMENT ON COLUMN business.NODE.NODE_ID IS 'ノードID';
COMMENT ON COLUMN business.NODE.NODE_TYPE IS 'ノード種別';
COMMENT ON COLUMN business.NODE.HIERARCHY IS '階層';
COMMENT ON COLUMN business.NODE.NODE_NM_LGC IS 'ノード名（論理）';
COMMENT ON COLUMN business.NODE.NODE_NM_PSC IS 'ノード名（物理）';
COMMENT ON COLUMN business.NODE.CONTENTS_ID IS 'コンテンツID';
COMMENT ON COLUMN business.NODE.UP_DT IS '更新日';
COMMENT ON COLUMN business.NODE.UP_NM IS '更新者';
COMMENT ON COLUMN business.NODE.RG_DT IS '登録日';
COMMENT ON COLUMN business.NODE.RG_NM IS '登録者';
COMMENT ON TABLE business.NODE IS 'ノード';
