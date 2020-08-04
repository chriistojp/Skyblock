package org.skysurge.skyblock.tpa;

/**
 * Copy Right ©
 * This code is private
 * Owner: Christo
 * From: 10/22/19-2023
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

import org.bukkit.entity.Player;

/**
 *
 * @author SkyLordJay_
 *
 */

public class TPA {

    public Player p;
    public Long time;

    public TPA(Player p) {
        this.p = p;
        time = System.currentTimeMillis();
    }

}
