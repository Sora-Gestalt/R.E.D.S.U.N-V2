import { useRef } from "react";
import { motion, useScroll, useTransform, MotionValue } from "framer-motion";

export default function HeroSection() {
  const containerRef = useRef<HTMLDivElement>(null);
  
  const { scrollYProgress } = useScroll({
    target: containerRef,
    offset: ["start start", "end end"],
  });

  return (
    <div ref={containerRef} className="relative h-[700vh] bg-[#030916] text-white font-sans antialiased selection:bg-red-500/30">
      
      <div className="sticky top-0 left-0 h-screen w-full overflow-hidden z-0">
        <video
          autoPlay
          loop
          muted
          playsInline
          preload="auto"
          className="absolute inset-0 w-full h-full object-cover brightness-[0.45]"
          onError={(e) => console.error("Video element loading fallback:", e)}
        >
          <source 
            src="https://assets.mixkit.co/videos/preview/mixkit-abstract-laser-lights-background-loop-42220-large.mp4" 
            type="video/mp4" 
          />
        </video>
        <div className="absolute inset-0 bg-[linear-gradient(to_right,#ffffff03_1px,transparent_1px),linear-gradient(to_bottom,#ffffff03_1px,transparent_1px)] bg-[size:40px_40px]" />
        <div className="absolute inset-0 bg-radial-vignette" />
      </div>

      <div className="sticky top-0 h-screen w-full flex flex-col justify-center px-8 md:px-24 lg:px-32 z-10 pointer-events-none">
        
        <div className="relative w-full max-w-4xl h-96">
          
          <ScrollArticle 
            progress={scrollYProgress} 
            range={[0.0, 0.14]} 
            tag="01 // INTRODUCTION"
            title="About Me" 
          />
          
          <ScrollArticle 
            progress={scrollYProgress} 
            range={[0.14, 0.28]} 
            tag="02 // IDENTITY"
            title="Who Am I ?" 
          />
          
          <ScrollArticle 
            progress={scrollYProgress} 
            range={[0.28, 0.42]} 
            tag="03 // MISSION"
            title="The Journey"
            description="A Software Engineering Student At King Saud University Who Is Deeply Passionate About Solving Complex Problems And Crafting Elegant Digital Infrastructure." 
          />
          
          <ScrollArticle 
            progress={scrollYProgress} 
            range={[0.42, 0.56]} 
            tag="04 // PORTFOLIO"
            title="My Projects" 
          />
          
          <ScrollArticle 
            progress={scrollYProgress} 
            range={[0.56, 0.70]} 
            tag="05 // FEATURED WORK"
            title="Selected Systems"
            description="Engineering data pipelines for a Used Car Prices KSA Prediction Model, alongside designing robust architecture for an enterprise HMS System using OOP & GUI principles in Java." 
          />
          
          <ScrollArticle 
            progress={scrollYProgress} 
            range={[0.70, 0.84]} 
            tag="06 // KNOWLEDGE"
            title="My Certificates" 
          />
          
          <ScrollArticle 
            progress={scrollYProgress} 
            range={[0.84, 1.0]} 
            tag="07 // EXPERTISE"
            title="My Skills" 
          />
          
        </div>
      </div>
    </div>
  );
}

interface ScrollArticleProps {
  tag: string;
  title: string;
  description?: string;
  progress: MotionValue<number>;
  range: [number, number];
}

function ScrollArticle({ tag, title, description, progress, range }: ScrollArticleProps) {
  const opacity = useTransform(
    progress,
    [range[0], range[0] + 0.02, range[1] - 0.02, range[1]],
    [0, 1, 1, 0]
  );
  
  const y = useTransform(progress, [range[0], range[1]], [40, -40]);

  return (
    <motion.div
      style={{ opacity, y }}
      className="absolute inset-0 flex flex-col justify-center pointer-events-auto max-w-3xl"
    >
      <span className="font-mono text-xs md:text-sm tracking-[0.2em] text-stone-400 mb-3 block uppercase">
        {tag}
      </span>

      <h1 className="text-4xl md:text-6xl lg:text-7xl font-light tracking-tight text-stone-100 leading-none mb-6">
        {title}
      </h1>

      {description && (
        <p className="text-base md:text-lg lg:text-xl text-stone-400 font-light leading-relaxed max-w-2xl border-l border-stone-700 pl-6 mt-2">
          {description}
        </p>
      )}
    </motion.div>
  );
}