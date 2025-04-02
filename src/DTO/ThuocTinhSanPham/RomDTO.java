/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

/**
 *
 * @author thaoh
 */
public class RomDTO {
    private int madungluongrom;
    private int dungluongrom;

    public RomDTO(int madungluongrom, int dungluongrom) {
        this.madungluongrom = madungluongrom;
        this.dungluongrom = dungluongrom;
    }

    public RomDTO() {
    }

    public int getMadungluongrom() {
        return madungluongrom;
    }

    public void setMadungluongrom(int madungluongrom) {
        this.madungluongrom = madungluongrom;
    }

    public int getDungluongrom() {
        return dungluongrom;
    }

    public void setDungluongrom(int dungluongrom) {
        this.dungluongrom = dungluongrom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.madungluongrom;
        hash = 59 * hash + this.dungluongrom;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RomDTO other = (RomDTO) obj;
        if (this.madungluongrom != other.madungluongrom) {
            return false;
        }
        return this.dungluongrom == other.dungluongrom;
    }

    @Override
    public String toString() {
        return "RomDTO{" + "madungluongrom=" + madungluongrom + ", dungluongrom=" + dungluongrom + '}';
    }    
}
