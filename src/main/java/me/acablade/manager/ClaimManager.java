package me.acablade.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import me.acablade.db.OzDatabase;
import me.acablade.objects.Claim;

public class ClaimManager implements Runnable{

    public static final long PERIOD = 60;
    
    
    private Map<Long, Claim> claimMap = new HashMap<>();
    private OzDatabase database;

    public ClaimManager(OzDatabase database){
        this.database = database;
        init();
    }

    private void init(){
        claimMap.clear();
        database.getAllClaims().forEach(c -> claimMap.put(c.getId(), c));
    }

    public void add(Claim claim){
        this.claimMap.put(claim.getId(), claim);
        database.addClaim(claim);
    }

    public void remove(long id){
        this.claimMap.remove(id);
        database.removeClaim(id);
    }

    public Claim get(long id){
        return this.claimMap.get(id);
    }

    public Collection<Claim> getAllClaims(){
        return this.claimMap.values();
    }

    public boolean contains(long id){
        return this.claimMap.containsKey(id);
    }


    @Override
    public void run() {
        for(Claim claim: claimMap.values()){
            claim.tick();
            if(claim.shouldRemove()) remove(claim.getId());
        }
    }

    
}
