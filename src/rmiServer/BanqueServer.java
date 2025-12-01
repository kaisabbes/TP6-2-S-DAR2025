package rmiServer;

import rmiService.BanqueImpl;
import rmiService.IBanque;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;

public class BanqueServer {
    public static void main(String[] args) {
        try {
            // Démarrer le registre RMI sur 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry démarré sur 1099");

            // Préparation de l'environnement JNDI
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

            // Contexte JNDI
            Context context = new InitialContext(env);

            // Créer le service
            IBanque banque = new BanqueImpl();

            // Enregistrement avec un nom logique SIMPLE
            context.rebind("Banque", banque);

            System.out.println("Objet distant 'Banque' enregistré via JNDI.");
            System.out.println("Serveur RMI prêt.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
