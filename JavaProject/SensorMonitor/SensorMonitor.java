import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SensorMonitor {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SensorTask(), 0, 60000); // Lee el sensor cada minuto
    }

    static class SensorTask extends TimerTask {
        @Override
        public void run() {
            // Leer la temperatura del sensor (simulado aquí)
            double temperatura = leerTemperatura();

            // Comprobar si la temperatura es mayor o igual a 70 grados
            if (temperatura >= 50.0) {
                // Enviar notificación de alerta
                NotificationSender.sendNotification("¡Alerta! La temperatura ha alcanzado los 70 grados Celsius.");
            }
        }

        private double leerTemperatura() {
            return Math.random() * 100; // Simula valores de temperatura entre 0 y 100 grados
        }
    }

    static class NotificationSender {
        public static void sendNotification(String message) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("notify-send", "Temperatura", message);
                Process process = processBuilder.start();
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}