INSERT INTO AD (AD_ID, DAY, TIME_SLOT, COST, PRICE, BUNDLE_ID, TIER, TYPE)
VALUES (1, '9:00-10:00', 'THURSDAY', 30000, 12000, 1, 'PRIME', 'CABLE'),
       (2, '7:00-8:00', 'SUNDAY', 90000, 40000, 1, 'PRIME', 'OTA'),
       (3, '4:00-5:00', 'MONDAY', 20000, 10000, 2, 'SUB-PRIME', 'CABLE'),
       (4, '1:00-3:00', 'TUESDAY', 10000, 5000, 2, 'SUB-PRIME', 'OTA'),
       (5, '1:00-3:00', 'SATURDAY', 100000, 80000, 1, 'PRIME', 'CABLE');

INSERT INTO AD_BUNDLE (BUNDLE_ID, AD_IDS, BUNDLE_PRICE, TIER)
VALUES (1, '1,2,5', 195000, 'PRIME'),
       (2, '3,4', 90000, 'SUB-PRIME');
