package me.acablade.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import me.acablade.objects.Claim;

public class ClaimManager implements Runnable{

    private Map<Long, Claim> claimMap = new HashMap<>();

    public static final long PERIOD = 60;

    public void add(Claim claim){
        this.claimMap.put(claim.getId(), claim);
    }

    public void remove(long id){
        this.claimMap.remove(id);
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
        claimMap.entrySet().removeIf(claim -> {
            claim.getValue().tick();
            return claim.getValue().shouldRemove();
        });
    }

    
}
