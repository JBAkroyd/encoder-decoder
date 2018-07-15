encoder-decoder

cat big.java | java Encoder 11 > encoder.txt
cat encoder.txt | java BitPacker > bitpacker.txt
cat bitpacker.txt | java BitUnpacker > bitunpacker.txt
cat bitunpacker.txt | java Decoder > decoder.java
md5sum big.txt
md5sum decoder.txt



