package FirstTask;

enum Countries {
    RUSSIA,
    SWEDEN,
    CHINA,
    ITALY,
    GERMANY,
    BINOCLA,
    ITIS,
    ANDORRA,
    SPAIN,
    DENMARK,
    DC,
    MARVEL,
    APPLE,
    SAMSUNG,
    DELL,
    STEAM,
    ORIGIN,
    JAVA,
    C_PLUS {
        @Override
        public String toString() {
            return "C++";
        }
    },
    C_SHARP {
        @Override
        public String toString() {
            return "C#";
        }
    }
}