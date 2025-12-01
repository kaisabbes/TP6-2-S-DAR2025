package rmiService;

import metier.Compte;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {

    private Map<Integer, Compte> comptes = new HashMap<>();

    public BanqueImpl() throws RemoteException {
        super();
    }

    @Override
    public String creerCompte(Compte c) throws RemoteException {
        comptes.put(c.getCode(), c);
        return "Compte créé avec succès : " + c.getCode();
    }

    @Override
    public String getInfoCompte(int code) throws RemoteException {
        Compte c = comptes.get(code);
        if (c == null) return "Compte non trouvé.";
        return c.toString();
    }
}
