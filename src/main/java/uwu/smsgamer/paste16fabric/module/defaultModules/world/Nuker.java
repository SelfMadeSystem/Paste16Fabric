package uwu.smsgamer.paste16fabric.module.defaultModules.world;

import lombok.Getter;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.*;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.UpdateEvent;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.utils.*;
import uwu.smsgamer.paste16fabric.values.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

// Wurst paste.
public class Nuker extends PasteModule {
    public final VNumber range = new VNumber(this, "Range", 5, 3, 8, 0.1,
      "Hit block range.");
    public final VSelect<NukerMode> mode = new VSelect<>(this, "Mode", 0, "Mode for this nuker.", NukerMode.values());

    public Nuker() {
        super("Nuker", "Destroys blocks around you.", ModuleCategory.WORLD);
    }

    private final ArrayDeque<Set<BlockPos>> prevBlocks = new ArrayDeque<>();
    private BlockPos currentBlock;
//    private float progress;
//    private float prevProgress;

    @PasteListener
    public void onUpdate(UpdateEvent event) {
        if (!getState()) return;
// abort if using IDNuker without an ID being set
//        if(mode.getSelected() == Mode.ID && id.getBlock() == Blocks.AIR)
//            return;

        ClientPlayerEntity player = mc.player;

        currentBlock = null;
        assert mc.player != null;
        Vec3d eyesPos = PlayerUtils.getEyePos().subtract(0.5, 0.5, 0.5);
        BlockPos eyesBlock = new BlockPos(PlayerUtils.getEyePos());
        double rangeSq = Math.pow(range.getDouble(), 2);
        int blockRange = (int) Math.ceil(range.getDouble());

        Vec3i rangeVec = new Vec3i(blockRange, blockRange, blockRange);
        BlockPos min = eyesBlock.subtract(rangeVec);
        BlockPos max = eyesBlock.add(rangeVec);

        ArrayList<BlockPos> blocks = BlockUtils.getAllInBox(min, max);
        Stream<BlockPos> stream = blocks.parallelStream();

        List<BlockPos> blocks2 = stream
          .filter(pos -> eyesPos.squaredDistanceTo(Vec3d.of(pos)) <= rangeSq)
          .filter(pos -> BlockUtils.canBeClicked(pos))
          .filter(mode.getSelected().getValidator(this))
          .sorted(Comparator.comparingDouble(
            pos -> eyesPos.squaredDistanceTo(Vec3d.of(pos))))
          .collect(Collectors.toList());

        if (player.abilities.creativeMode) {
            Stream<BlockPos> stream2 = blocks2.parallelStream();
            for (Set<BlockPos> set : prevBlocks)
                stream2 = stream2.filter(pos -> !set.contains(pos));
            List<BlockPos> blocks3 = stream2.collect(Collectors.toList());

            prevBlocks.addLast(new HashSet<>(blocks3));
            while (prevBlocks.size() > 5)
                prevBlocks.removeFirst();

            if (!blocks3.isEmpty())
                currentBlock = blocks3.get(0);

            mc.interactionManager.cancelBlockBreaking();
//            progress = 1;
//            prevProgress = 1;
            BlockBreaker.breakBlocksWithPacketSpam(blocks3);
            return;
        }

        for (BlockPos pos : blocks2)
            if (BlockBreaker.breakOneBlock(pos)) {
                currentBlock = pos;
                break;
            }

        if (currentBlock == null)
            mc.interactionManager.cancelBlockBreaking();

//        if (currentBlock != null && BlockUtils.getHardness(currentBlock) < 1) {
//            prevProgress = progress;
//            progress = IMC.getInteractionManager().getCurrentBreakingProgress();
//
//            if (progress < prevProgress)
//                prevProgress = progress;
//
//        } else {
//            progress = 1;
//            prevProgress = 1;
//        }
    }

    private enum NukerMode {
        NORMAL("Normal", (n, p) -> true),

//        ID("ID", (n, p) -> BlockUtils.getName(p).equals(n.id.getBlockName())),

//        MULTI_ID("MultiID", (n, p) -> n.multiIdList.getBlockNames().contains(BlockUtils.getName(p))),

        FLAT("Flat", (n, p) -> p.getY() >= mc.player.getPos().getY()),

        SMASH("Smash", (n, p) -> BlockUtils.getHardness(p) >= 1);
        @Getter
        private final String name;
        private final BiPredicate<Nuker, BlockPos> validator;

        NukerMode(String name, BiPredicate<Nuker, BlockPos> validator) {
            this.name = name;
            this.validator = validator;
        }

        public Predicate<BlockPos> getValidator(Nuker n)
        {
            return p -> validator.test(n, p);
        }
    }
}
