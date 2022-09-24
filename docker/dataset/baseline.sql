CREATE TABLE iris (
                      "Id" INT,
                      "SepalLengthCm" DOUBLE,
                      "SepalWidthCm" DOUBLE,
                      "PetalLengthCm" DOUBLE,
                      "PetalWidthCm" DOUBLE,
                      "Species" TEXT
);
COPY iris FROM 'Iris.csv';
