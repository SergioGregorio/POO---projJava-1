
package View;

class MyThread implements Runnable {
    @Override
    public void run() {
        // Lógica que você deseja executar em uma thread separada
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread em execução: " + i);
            try {
                Thread.sleep(500); // Simula uma tarefa demorada
            } catch (InterruptedException e) {
                // Tratar interrupção da thread
                Thread.currentThread().interrupt();
            }
        }
    }
}

