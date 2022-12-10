package me.acablade.db;

import java.util.Collection;

import me.acablade.objects.Claim;

public interface OzDatabase {

    public Collection<Claim> getAllClaims();
    public Claim getClaim(long id);
    public void addClaim(Claim claim);
    public void removeClaim(long id);
    public void updateClaim(Claim claim);

    
}
