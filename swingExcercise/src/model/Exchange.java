package model;

import model.entities.ICurrency;

public class Exchange implements IExchange{
    @Override
    public double exchange(ICurrency src, ICurrency tgt, double amt) {
        return src.getRate() * amt / tgt.getRate();
    }
}
