package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.*;

import java.util.ArrayList;

// Wurst paste.
public class BlockUtils implements MinecraftHelper {
    public static BlockState getState(BlockPos pos)
    {
        return mc.world.getBlockState(pos);
    }

    public static Block getBlock(BlockPos pos)
    {
        return getState(pos).getBlock();
    }

    public static int getId(BlockPos pos)
    {
        return Block.getRawIdFromState(getState(pos));
    }

    public static String getName(BlockPos pos)
    {
        return getName(getBlock(pos));
    }

    public static String getName(Block block)
    {
        return Registry.BLOCK.getId(block).toString();
    }

    public static Block getBlockFromName(String name)
    {
        try
        {
            return Registry.BLOCK.get(new Identifier(name));

        }catch(InvalidIdentifierException e)
        {
            return Blocks.AIR;
        }
    }

    public static float getHardness(BlockPos pos)
    {
        return getState(pos).calcBlockBreakingDelta(mc.player, mc.world, pos);
    }

    private static VoxelShape getOutlineShape(BlockPos pos)
    {
        return getState(pos).getOutlineShape(mc.world, pos);
    }

    public static Box getBoundingBox(BlockPos pos)
    {
        return getOutlineShape(pos).getBoundingBox().offset(pos);
    }

    public static boolean canBeClicked(BlockPos pos)
    {
        return getOutlineShape(pos) != VoxelShapes.empty();
    }

    public static ArrayList<BlockPos> getAllInBox(BlockPos from, BlockPos to)
    {
        ArrayList<BlockPos> blocks = new ArrayList<>();

        BlockPos min = new BlockPos(Math.min(from.getX(), to.getX()),
          Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
        BlockPos max = new BlockPos(Math.max(from.getX(), to.getX()),
          Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));

        for(int x = min.getX(); x <= max.getX(); x++)
            for(int y = min.getY(); y <= max.getY(); y++)
                for(int z = min.getZ(); z <= max.getZ(); z++)
                    blocks.add(new BlockPos(x, y, z));

        return blocks;
    }
}
