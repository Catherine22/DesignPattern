package com.catherine.abstract_factory;

import com.catherine.abstract_factory.brand_factory.Brand;
import com.catherine.abstract_factory.color_factory.Color;

/**
 * Created by Catherine on 2016/10/4.
 * Soft-World Inc.
 * catherine919@soft-world.com.tw
 */

public abstract class CarAbstractFactory {
    public abstract Color getColor(int color);
    public abstract Brand getBrand(int brand);
}
