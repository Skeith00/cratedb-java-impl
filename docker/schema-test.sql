CREATE TABLE asset (
      id INTEGER primary key,
      code TEXT,
      title TEXT,
      type ARRAY(TEXT),
      created_at TIMESTAMP WITHOUT TIME ZONE,
      metadata ARRAY(OBJECT(DYNAMIC) AS (
          id INTEGER,
          key TEXT,
          value TEXT
      )),
      location GEO_SHAPE,
      active BOOLEAN
);

insert into asset (id, code, title, type, created_at, metadata, geometry, active) values (
  1,
  'test code',
  'test title',
  ['financial']
  '2021-03-09',
  [
    {
      "id" = 1,
      "key" = 'key1',
      "value" = 'value1'
    },
    {
      "id" = 1,
      "key" = 'key1',
      "value" = 'value1'
    }
  ],
  {
    type = 'Polygon',
    coordinates = [
      [
        [
          -8.4375,
          43.13306116240612
        ],
        [
          -9.140625,
          37.23032838760387
        ],
        [
          -2.197265625,
          36.527294814546245
        ],
        [
          2.724609375,
          42.48830197960227
        ],
        [
          -8.4375,
          43.13306116240612
        ]
      ]
    ]
  },
  true
);
SELECT * FROM asset ORDER BY id;
SELECT * FROM asset
WHERE match(geometry, {
  type='Polygon',
  coordinates=[
    [
      [
        0.62896728515625,
        41.634305152774054
      ],
      [
        0.608367919921875,
        41.6244253119973
      ],
      [
        0.5968666076660156,
        41.6055595934195
      ],
      [
        0.6396102905273438,
        41.59015491609044
      ],
      [
        0.6753158569335938,
        41.60735656626029
      ],
      [
        0.6499099731445312,
        41.63597302844412
      ],
      [
        0.62896728515625,
        41.634305152774054
      ]
    ]
  ]
});
DELETE FROM asset where id = 1;
DROP TABLE asset;
