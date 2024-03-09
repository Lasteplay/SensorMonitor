import java.awt.Desktop;
import java.io.File;
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
            double temperatura = leerTemperatura();

            // Modificar a gusto
            if (temperatura >= 70.0) {
                abrirArchivoHTML("/home/usuario/Descargas/vscode/JavaProject/SensorMonitor2.0/Pages/index.html");
            }
        }

        private double leerTemperatura() {
            return Math.random() * 100; // Simula valores de temperatura entre 0 y 100 grados
        }

        private void abrirArchivoHTML(String rutaArchivo) {
            File archivoHTML = new File(rutaArchivo);
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(archivoHTML.toURI());
                    } else {
                        System.out.println("La acción de navegación no es compatible en este sistema.");
                    }
                } else {
                    System.out.println("La funcionalidad de escritorio no es compatible en este sistema.");
                }
            } catch (IOException | UnsupportedOperationException e) {
                System.err.println("Error al abrir el archivo HTML: " + e.getMessage());
            }
        }
    }
}
