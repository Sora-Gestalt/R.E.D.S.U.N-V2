import { useState, useRef, useEffect, type ReactNode, type Dispatch, type SetStateAction } from "react";
import { motion } from "framer-motion";

interface TabProps {
    id: string; 
    children: ReactNode;
    color: string;
    activeTabId: string;
    setPosition: Dispatch<SetStateAction<Position>>;
    setActivePosition: Dispatch<SetStateAction<Position>>;
    setActiveTabId: Dispatch<SetStateAction<string>>;
}

type Position = {
    width: number;
    left: number;
    opacity: number;
    color: string;
};

function Cursor({ position }: { position: Position }) {
    return (
        <motion.li
            animate={{
                left: position.left,
                width: position.width,
                opacity: position.opacity,
                backgroundColor: position.color,
            }}
            transition={{ type: "spring", stiffness: 350, damping: 28 }}
            className="absolute z-0 h-7 rounded-full md:h-12 shadow-[0_0_25px_rgba(26,103,248,0.25)]"
        />
    );
}

function Tab({ id, children, color, activeTabId, setPosition, setActivePosition, setActiveTabId }: TabProps) {
    const ref = useRef<HTMLLIElement | null>(null);

    function getDimensions(customOpacity = 1) {
        if (!ref.current) return null;
        const { width } = ref.current.getBoundingClientRect();
        return {
            left: ref.current.offsetLeft,
            width,
            opacity: customOpacity,
            color,
        };
    }

    useEffect(() => {
        if (id === activeTabId && ref.current) {
            const dims = getDimensions(0.6); 
            if (dims) {
                setPosition(dims);
                setActivePosition(dims);
            }
        }
    }, [activeTabId]); 

    function handleMouseEnter() {
        const dims = getDimensions(0.85);
        if (dims) setPosition(dims);
    }

    function handleResetToActive() {
        const dims = getDimensions(0.85);
        if (dims) {
            setActiveTabId(id);
            setActivePosition(getDimensions(0.35)!);
            setPosition(dims);
        }
    }

    return (
        <li 
            ref={ref}
            onMouseEnter={handleMouseEnter}
            onClick={handleResetToActive}
            className="relative z-10 block cursor-pointer px-3 py-1.5 text-xs font-medium uppercase tracking-wider text-[#FFFDFB] md:px-5 md:py-3 md:text-sm select-none"
        >
            {children}
        </li>
    );
}

export default function SlideBar() {
    const [activeTabId, setActiveTabId] = useState<string>("home");

    
    const [position, setPosition] = useState<Position>({
        width: 0,
        left: 0,
        opacity: 0,
        color: "#1A67F8",
    });

    const [activePosition, setActivePosition] = useState<Position>({
        width: 0,
        left: 0,
        opacity: 0,
        color: "#1A67F8",
    });

    return (
       
        <div className="bg-[#050D21] py-20 flex items-center justify-center">
            <ul 
                onMouseLeave={() => {
                    setPosition(activePosition);
                }}
             
                className="relative mx-auto flex w-fit rounded-full border border-[#C9D5EE]/20 bg-[#050D21]/60 p-1.5 backdrop-blur-md shadow-2xl"
            >
              
                <Tab id="home" color="#1A67F8" activeTabId={activeTabId} setActiveTabId={setActiveTabId} setPosition={setPosition} setActivePosition={setActivePosition}>Home</Tab>
                <Tab id="blogs" color="#bd1af8" activeTabId={activeTabId} setActiveTabId={setActiveTabId} setPosition={setPosition} setActivePosition={setActivePosition}>Blogs</Tab>
                <Tab id="expertise" color="#841dd2" activeTabId={activeTabId} setActiveTabId={setActiveTabId} setPosition={setPosition} setActivePosition={setActivePosition}>Expertise</Tab>
                <Cursor position={position} />
            </ul>
        </div>
    );
}