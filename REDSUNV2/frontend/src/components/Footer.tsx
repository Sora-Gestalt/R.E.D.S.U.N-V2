import { useState, type ReactNode } from "react";
import githubLogo from "../assets/github.png";
import gitlabLogo from "../assets/gitlab(1).png";
import dockerLogo from "../assets/social.png";
import linkedInLogo from "../assets/linkedin(1).png";
import kaggleLogo from "../assets/kaggle-svgrepo-com.png";
import credlyLogo from "../assets/credly-svgrepo-com.png";
import gmailLogo from "../assets/gmail-svgrepo-com.png";
import springBootLogo from "../assets/spring-boot-svgrepo-com.png";
import restApiLogo from "../assets/rest-api-svgrepo-com.png";
import reactLogo from "../assets/react-svgrepo-com.png";

export default function Footer() {
    return (
        <footer className="w-full bg-[rgb(5,13,33)] text-gray-300 py-16 px-6 border-t border-gray-800">
            <div className="max-w-6xl mx-auto flex flex-col gap-2">
                
                <div className="hidden md:grid grid-cols-4 gap-8 text-md font-semibold text-white mb-2">
                    <h1 className="whitespace-nowrap">Explore</h1>
                    <h1 className="whitespace-nowrap md:pl-8">Contact</h1>
                    <h1 className="col-span-2 whitespace-nowrap md:pl-8">My Activity</h1>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-4 gap-8 items-start">
         
                    <ul className="flex flex-col gap-4">
                        <h2 className="text-lg font-semibold text-gray-400 md:hidden">Check My Accounts</h2>
                        <div className="flex flex-wrap gap-3">
                            <ButtonTab imgSource={githubLogo} alt="GitHub" />
                            <ButtonTab imgSource={gitlabLogo} alt="GitLab" />
                            <ButtonTab imgSource={dockerLogo} alt="Docker" />
                            <ButtonTab imgSource={linkedInLogo} alt="LinkedIn" />
                            <ButtonTab imgSource={credlyLogo} alt="Credly"/>
                            <ButtonTab imgSource={kaggleLogo} alt="Kaggle"/>
                        </div>
                    </ul>
                  
                    <ul className="flex flex-col gap-4 border-t border-gray-800 pt-6 md:border-t-0 md:pt-0 md:border-l md:border-gray-800 md:pl-8">
                        <h2 className="text-lg font-semibold text-gray-400 md:hidden">My Email</h2>
                        <div className="flex justify-center">
                            <ButtonTab imgSource={gmailLogo} alt="Gmail"></ButtonTab>
                        </div>
                    </ul>
  
                    <ul className="flex flex-col gap-4 border-t border-gray-800 pt-6 md:border-t-0 md:pt-0 md:border-l md:border-gray-800 md:pl-8 w-full">
                        <h2 className="text-lg font-semibold text-gray-400 md:hidden">Skills I'm Currently Learning</h2>
                        <div className="flex flex-col gap-2 w-full">
                            <Card skillName="Spring Boot" imgSource={springBootLogo}></Card>
                            <Card skillName="React native" imgSource={reactLogo}></Card>
                            <Card skillName="Rest API" imgSource={restApiLogo}></Card>
                        </div>
                    </ul>

                    <ul className="flex flex-col gap-4 border-t border-gray-800 pt-6 md:border-t-0 md:pt-0 md:border-l md:border-gray-800 md:pl-8">
                        <h2 className="text-lg font-semibold text-gray-400 md:hidden">My Latest Contributes</h2>
                        <p className="text-gray-400 text-sm leading-relaxed">Open source PRs and infrastructure scaling...</p>
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                    </ul>

                </div>
            </div>
        </footer>
    );
}

interface TabProps {
    children: ReactNode;
}

function Tab({ children }: TabProps) {
    return (
        <li>{children}</li>
    );
}

interface ButtonTabProps {
    children?: ReactNode; 
    imgSource: string;
    alt: string;
}

function ButtonTab({ children, imgSource, alt }: ButtonTabProps) {
    const [bgClass, setBgClass] = useState<string>("bg-white/5 border-white/10");

    function handleMouseEnter() {
        setBgClass("bg-white/15 border-white/20 scale-105");
    }

    function handleMouseLeave() {
        setBgClass("bg-white/5 border-white/10");
    }

    function handleMouseClick() {
        setBgClass("bg-[rgb(29,78,216)] border-blue-400 scale-95");
    }

    return (
        <div className="flex items-center gap-3">
            <button 
                onMouseEnter={handleMouseEnter} 
                onMouseLeave={handleMouseLeave}
                onClick={handleMouseClick}
                className={`w-12 h-12 p-2.5 flex items-center justify-center border rounded-xl transform transition-all duration-200 outline-none ${bgClass}`}
            >
                <img src={imgSource} alt={alt} className="w-full h-full object-contain filter drop-shadow-md" />
            </button>
            {children && <div className="flex-1 min-w-0">{children}</div>}
        </div>
    );
}

interface CardProps {
    imgSource: string;
    skillName: string;
}

function Card({ imgSource, skillName }: CardProps) {
    const [cardClass, setCardClass] = useState<string>("bg-white/5 border-white/10");

    function handleMouseEnter() {
        setCardClass("bg-white/15 border-white/20");
    }

    function handleMouseLeave() {
        setCardClass("bg-white/5 border-white/10");
    }

    return (
        <ul 
            onMouseEnter={handleMouseEnter}
            onMouseLeave={handleMouseLeave}
            className={`flex flex-row items-center justify-start gap-4 border rounded-xl p-3 w-full transform transition-all duration-200 active:scale-[0.98] hover:scale-[1.02] ${cardClass}`}
        >
            <li>
                <img src={imgSource} alt={skillName} className="w-6 h-6 object-contain" />
            </li>
            <li className="text-sm font-medium text-white">{skillName}</li>
        </ul>
    );
}