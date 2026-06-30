import { useState } from "react";
import { motion } from "framer-motion";

interface BlogTabProps {
    blogTitle: string;
    blogDescription: string;
    blogDate: string;
    blogExcerpt: string;
    blogSlug: string;
    blogSkillTags: string[];
}

export default function Blog() {
    const [title, setTitle] = useState<string>("Scaling Infrastructure with React & Tailwind");
    const [description, setDescription] = useState<string>("A deep dive into building modular, highly performing UI components with modern tech stacks.");
    const [date, setDate] = useState<string>("June 30, 2026");
    const [excerpt, setExcerpt] = useState<string>("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation...");
    const [slug, setSlug] = useState<string>("scaling-infrastructure-react-tailwind");
    const [skillTags, setSkillTags] = useState<Array<string>>(["React", "Tailwind CSS", "Spring Boot", "Architecture"]);

    return (
        <div className="bg-[#050D21] min-h-screen py-16 px-6 text-gray-300 flex justify-center items-center">
            <div className="max-w-3xl w-full">
                <motion.div
                    initial={{ opacity: 0, y: -5 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.15, ease: "easeOut" }}
                    className="mb-8 pl-1"
                >
                    <h1 className="text-3xl font-bold text-white tracking-tight">Recent Articles</h1>
                    <p className="text-gray-400 mt-1">Thoughts, guides, and engineering insights.</p>
                </motion.div>

                <ul className="space-y-6">
                    <BlogTab 
                        blogTitle={title} 
                        blogDescription={description} 
                        blogDate={date} 
                        blogExcerpt={excerpt}
                        blogSlug={slug}
                        blogSkillTags={skillTags}
                    />
                </ul>
            </div>
        </div>
    );
}

function BlogTab({ blogTitle, blogDescription, blogDate, blogExcerpt, blogSlug, blogSkillTags }: BlogTabProps) {
    return (
        <motion.li 
            initial={{ opacity: 0, y: 10, scale: 0.99 }}
            animate={{ opacity: 1, y: 0, scale: 1 }}
            transition={{ 
                duration: 0.2,
                type: "tween",
                ease: "easeOut"
            }}
            whileHover={{ scale: 1.005, y: -1 }}
            className="block list-none border border-white/10 bg-white/5 rounded-2xl p-6 md:p-8 backdrop-blur-md shadow-xl transition-all duration-150 hover:bg-white/10 hover:border-white/20"
        >
            <div className="flex flex-col gap-4">
                <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-3 border-b border-gray-800 pb-4">
                    <h2 className="text-xl md:text-2xl font-bold text-white tracking-tight">
                        {blogTitle}
                    </h2>
                    <span className="text-xs font-semibold text-gray-400 tracking-wider uppercase bg-white/5 border border-white/10 px-3 py-1 rounded-full w-fit whitespace-nowrap">
                        {blogDate}
                    </span>
                </div>
                
                <p className="text-gray-400 text-sm md:text-base leading-relaxed">
                    {blogDescription}
                </p>

                {/* Skill Tags */}
                <div className="flex flex-wrap gap-2 my-1">
                    {blogSkillTags.map((tag, idx) => (
                        <span 
                            key={idx} 
                            className="text-xs font-medium text-blue-400 bg-blue-500/10 border border-blue-500/20 px-2.5 py-0.5 rounded-md shadow-[0_0_15px_rgba(26,103,248,0.1)]"
                        >
                            {tag}
                        </span>
                    ))}
                </div>
                
                {/* Excerpt Content Box */}
                <p className="text-gray-300 text-sm leading-relaxed mt-1 bg-black/20 p-5 rounded-xl border border-white/5 font-normal">
                    {blogExcerpt}
                </p>

                {/* Action Footer */}
                <div className="flex justify-end mt-1">
                    <button 
                        onClick={() => console.log(`Navigating to: /blog/${blogSlug}`)}
                        className="text-xs font-semibold uppercase tracking-wider text-blue-400 hover:text-blue-300 transition-colors duration-150 outline-none flex items-center gap-1 group"
                    >
                        Read Full Article 
                        <span className="transform transition-transform duration-150 group-hover:translate-x-1">→</span>
                    </button>
                </div>
            </div>
        </motion.li>
    );
}