package com.Blood.Ware.asm;

import com.Blood.Ware.asm.visitors.BlockLiquidVisitor;
import com.Blood.Ware.asm.visitors.BlockRendererDispatcherVisitor;
import com.Blood.Ware.asm.visitors.BlockStateContainerVisitor;
import com.Blood.Ware.asm.visitors.EntityPlayerSPVisitor;
import com.Blood.Ware.asm.visitors.EntityRendererVisitor;
import com.Blood.Ware.asm.visitors.NetworkManagerVisitor;
import com.Blood.Ware.asm.visitors.VisGraphVisitor;
import java.util.Arrays;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class InternaliClassTransformer implements IClassTransformer {
   private static final String[] transformedClasses = new String[]{"net.minecraft.client.renderer.chunk.VisGraph", "net.minecraft.client.renderer.EntityRenderer", "net.minecraft.network.NetworkManager", "net.minecraft.client.renderer.BlockRendererDispatcher", "net.minecraft.block.state.BlockStateContainer$StateImplementation", "net.minecraft.block.BlockLiquid", "net.minecraft.client.entity.EntityPlayerSP"};

   public byte[] transform(String name, String transformedName, byte[] basicClass) {
      boolean isObfuscated = !name.equals(transformedName);
      int index = Arrays.asList(transformedClasses).indexOf(transformedName);
      return index != -1 ? this.transform(index, basicClass, isObfuscated) : basicClass;
   }

   public byte[] transform(int index, byte[] basicClass, boolean isObfuscated) {
      System.out.println("Transforming " + transformedClasses[index]);

      try {
         ClassReader classReader = new ClassReader(basicClass);
         ClassWriter classWriter = new ClassWriter(3);
         Object classVisitor;
         switch(index) {
         case 0:
            classVisitor = new VisGraphVisitor(classWriter, isObfuscated);
            break;
         case 1:
            classVisitor = new EntityRendererVisitor(classWriter, isObfuscated);
            break;
         case 2:
            classVisitor = new NetworkManagerVisitor(classWriter, isObfuscated);
            break;
         case 3:
            classVisitor = new BlockRendererDispatcherVisitor(classWriter, isObfuscated);
            break;
         case 4:
            classVisitor = new BlockStateContainerVisitor(classWriter, isObfuscated);
            break;
         case 5:
            classVisitor = new BlockLiquidVisitor(classWriter, isObfuscated);
            break;
         case 6:
            classVisitor = new EntityPlayerSPVisitor(classWriter, isObfuscated);
            break;
         default:
            classVisitor = new ClassVisitor(327680, classWriter) {
            };
         }

         classReader.accept((ClassVisitor)classVisitor, 0);
         return classWriter.toByteArray();
      } catch (Exception var7) {
         var7.printStackTrace();
         return basicClass;
      }
   }
}
