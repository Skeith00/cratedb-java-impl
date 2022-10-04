CREATE TABLE blogpost (
      id INTEGER primary key,
      title TEXT,
      body TEXT,
      categories ARRAY(TEXT),
      created_at TIMESTAMP WITHOUT TIME ZONE,
      metadata ARRAY(OBJECT(DYNAMIC) AS (
          key TEXT,
          value ARRAY(TEXT)
      )),
      location GEO_SHAPE,
      archived BOOLEAN
);

insert into blogpost (id, title, body, categories, created_at, metadata, location, archived) values (
  1,
  'test title',
  'This is a body test text'
  ['food', 'travel']
  '2021-03-09',
  [
    {
      "key" = 'country',
      "value" = ['philippines']
    },
    {
      "key" = 'food',
      "value" = ['halo-halo', 'empanada']
    }
  ],
  {
    type = 'Polygon',
    coordinates = [
      [
        [
            120.32226562500001,
            19.518375478601566
        ],
        [
            116.01562499999999,
            7.536764322084078
        ],
        [
            125.90332031249999,
            4.915832801313164
        ],
        [
            128.2763671875,
            7.667441482726068
        ],
        [
            123.04687499999999,
            19.642587534013032
        ],
        [
            120.32226562500001,
            19.518375478601566
        ]
      ]
    ]
  },
  false
);
SELECT * FROM blogpost ORDER BY id;
SELECT * FROM blogpost
WHERE match(location, {
  type='Polygon',
  coordinates=[
    [
        [
            120.90179443359375,
            14.505155836749273
        ],
        [
            121.1407470703125,
            14.505155836749273
        ],
        [
            121.1407470703125,
            14.676596946649664
        ],
        [
            120.90179443359375,
            14.676596946649664
        ],
        [
            120.90179443359375,
            14.505155836749273
        ]
    ]
  ]
});
DELETE FROM blogpost where id = 1;
DROP TABLE blogpost;
