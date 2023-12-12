package app.projects.stressless_app;

public class Data {
        public String text;
        public String type;

        public Data() {
            // Default constructor required for calls to DataSnapshot.getValue(MyData.class)
        }

        public Data(String text, String type) {
            this.text = text;
            this.type = type;
        }

}
