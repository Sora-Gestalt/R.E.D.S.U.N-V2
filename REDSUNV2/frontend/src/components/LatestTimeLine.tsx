import { useState, useRef } from "react";
import { Timeline, TimelineItem as MuiTimelineItem, TimelineSeparator, TimelineConnector, TimelineContent, TimelineDot, TimelineOppositeContent } from "@mui/lab";
import { motion, useScroll, useTransform } from "framer-motion";

interface TimelineItemType {
  id: string;
  header: string;
  content: string;
  date: string;
  classColor: string;
  headerColor: string;
}

export default function LatestTimeLine() {
  const [hoveredId, setHoveredId] = useState<string | null>(null);
  
  const [items] = useState<Array<TimelineItemType>>([
    {
      id: "1",
      header: "Used Car Prices Prediction Model",
      date: "2026",
      classColor: "bg-cyan-500 shadow-[0_0_12px_rgba(6,182,212,0.6)]",
      headerColor: "text-cyan-400",
      content: "Built a used cars prices in KSA prediction model with scikit-learn on real-world data."
    },
    {
      id: "2",
      header: "HMS (Hotel Management System)",
      date: "2026",
      classColor: "bg-violet-500 shadow-[0_0_12px_rgba(139,92,246,0.6)]",
      headerColor: "text-violet-400",
      content: "Built an OOP/GUI Project that mimics a Hotel Management System using Java."
    },
    {
      id: "3",
      header: "Machine Learning Specialization",
      date: "2025",
      classColor: "bg-fuchsia-500 shadow-[0_0_12px_rgba(217,70,239,0.6)]",
      headerColor: "text-fuchsia-400",
      content: "Finished Coursera's Machine Learning Specialization by DeepLearning.AI."
    }
  ]);

  const timelineContainerVariants = {
    hidden: { opacity: 1 },
    visible: {
      opacity: 1,
      transition: { staggerChildren: 0.3 } 
    }
  };

  const connectorVariants = {
    hidden: { scaleY: 0 },
    visible: { 
      scaleY: 1, 
      transition: { duration: 0.5, ease: "easeInOut" } 
    }
  };

  const contentVariants = {
    hidden: { opacity: 0, x: -10 },
    visible: { 
      opacity: 1, 
      x: 0, 
      transition: { duration: 0.4 } 
    }
  };

  return (
    <div className="bg-[rgb(5,13,33)] min-h-screen py-12 px-4 font-sans text-slate-200 overflow-x-hidden">
      <div className="max-w-3xl mx-auto">
        <h2 className="text-center text-3xl font-extrabold text-white mb-12 tracking-wide">
          MY LATEST CONTRIBUTIONS
        </h2>

        <motion.div
          variants={timelineContainerVariants}
          initial="hidden"
          whileInView="visible"
          viewport={{ once: true, margin: "-100px" }}
        >
          <Timeline position="alternate">
            {items.map((item, index) => {
              const isAnyItemHovered = hoveredId !== null;
              const isCurrentHovered = hoveredId === item.id;
              const opacityClass = isAnyItemHovered && !isCurrentHovered ? "opacity-20 scale-100" : "opacity-100 scale-110";

              return (
                <MuiTimelineItem 
                  key={item.id}
                  onMouseEnter={() => setHoveredId(item.id)}
                  onMouseLeave={() => setHoveredId(null)}
                  className={`transition-opacity duration-300 ease-in-out ${opacityClass}`}
                >
                  <TimelineOppositeContent className="text-slate-400 font-medium pt-4 text-sm md:text-base">
                    {item.date}
                  </TimelineOppositeContent>
                  
                  <TimelineSeparator className="relative">
                    <ScrollLinkedDot classColor={item.classColor} />
                    
                    {index < items.length - 1 && (
                      <div className="h-full w-[2px] relative flex justify-center">
                        <motion.div 
                          variants={connectorVariants}
                          className="bg-slate-700 w-[2px] h-full origin-top"
                        />
                      </div>
                    )}
                  </TimelineSeparator>
                  
                  <TimelineContent className="pb-10 pt-2 px-4">
                    <motion.div
                      variants={contentVariants}
                      className={`p-4 rounded-xl border border-transparent transition-all duration-300 text-left inline-block max-w-xs ${
                        isCurrentHovered 
                          ? "bg-slate-800/40 border-slate-700/50 shadow-xl shadow-black/20" 
                          : "bg-transparent"
                      }`}
                    >
                      <h3 className={`${item.headerColor} font-bold text-lg md:text-xl transition-colors duration-200`}>
                        {item.header}
                      </h3>
                      <p className="text-slate-400 text-sm mt-1">
                        {item.content}
                      </p>
                    </motion.div>
                  </TimelineContent>
                </MuiTimelineItem>
              );
            })}
          </Timeline>
        </motion.div>
      </div>
    </div>
  );
}

function ScrollLinkedDot({ classColor }: { classColor: string }) {
  const dotRef = useRef<HTMLDivElement>(null);
  
  const { scrollYProgress } = useScroll({
    target: dotRef,
    offset: ["end bottom", "center center"],
  });

  const scale = useTransform(scrollYProgress, [0, 1], [0, 1]);
  const opacity = useTransform(scrollYProgress, [0, 0.8], [0, 1]);

  return (
    <motion.div ref={dotRef} style={{ scale, opacity }}>
      <TimelineDot className={`${classColor} border-0 z-10`} />
    </motion.div>
  );
}