package com.ianpert;

import java.util.Random;

public enum PhotoTypeEnum {
    DOG {
        @Override
        public String photoEndpoint() {
            return "https://place.dog/" + getEndopointTerminator();
        }
    },
    CAT {
        @Override
        public String photoEndpoint() {
            return "https://placekitten.com/" + this.getEndopointTerminator();
        }
    },
    BEAR {
        @Override
        public String photoEndpoint() {
            return "https://placebear.com/" + this.getEndopointTerminator();
        }
    };

    public String photoEndpoint() {
        return null;
    }

    public static PhotoTypeEnum lookup(String code) {
        if(code == null) {
            return null;
        }
        for (PhotoTypeEnum entry : values()) {
            if (code.equalsIgnoreCase(entry.name())) {
                return entry;
            }
        }
        return null;
    }

    private static final Random random = new Random();

    protected String getEndopointTerminator() {
        return getRandomizedDimension(random) + "/" + getRandomizedDimension(random);
    }

    protected String getRandomizedDimension(Random random) {
        return Integer.valueOf(random.nextInt(300, 800)).toString();
    }
}
