package com.kostenko.andrey.testconcord;

/**
 * Same reasons like CryptoHelper, many setting for next develop
 */
public class CryptoObject  {
    private final String key;
    private String content;
    private final boolean isEncode;
    private final boolean isKeyBase64;
    private final boolean isMessageBase64;
    private final boolean isReturnStringBase64;

    public CryptoObject(Builder builder) {
        this.key = builder.key;
        this.content = builder.content;
        this.isEncode = builder.isEncode;
        this.isKeyBase64 = builder.isKeyBase64;
        this.isMessageBase64 = builder.isMessageBase64;
        this.isReturnStringBase64 = builder.isReturnStringBase64;

        try {
            if (this.isEncode) {
                this.content = new String(encode());
            } else {
                this.content = new String(decode());
            }
        } catch (Exception e) {
            this.content = e.getMessage();
            e.printStackTrace();
        }
    }

    private byte[] encode() throws Exception{
        byte[] response = CryptoHelper.encode(key, content, isKeyBase64, isMessageBase64, isReturnStringBase64);
        return response;
    }

    private byte[] decode() throws Exception{
        byte[] response = CryptoHelper.decode(key, content, isKeyBase64, isMessageBase64);
        return response;
    }

    public String getContent() {
        return content;
    }



    public static class Builder {
        private final String key;
        private final String content;
        private final boolean isEncode;
        private boolean isKeyBase64;
        private boolean isMessageBase64;
        private boolean isReturnStringBase64;

        public Builder(String key, String content, boolean isEncode) {
            if (key == null || content == null) {
                throw new IllegalArgumentException();
            }
            this.key = key;
            this.content = content;
            this.isEncode = isEncode;
        }

        public Builder keyFormatBase64(boolean isKeyBase64){
            this.isKeyBase64 = isKeyBase64;
            return this;
        }

        public Builder messageFormatBase64(boolean isMessageBase64) {
            this.isMessageBase64 = isMessageBase64;
            return this;
        }

        public Builder stringResponseBase64(boolean isReturnStringBase64) {
            this.isReturnStringBase64 = isReturnStringBase64;
            return this;
        }

        public CryptoObject build() {
            return new CryptoObject(this);
        }
    }
}
