'use client'
import ReactMarkdown from 'react-markdown';

interface MarkdownPreviewProps {
  markdown: string;
}

const Preview: React.FC<MarkdownPreviewProps> = ({ markdown }) => {
  return (
    <div className="w-full h-screen p-4 border-l border-gray-300">
        <article className="prose lg:prose-xl">
            <ReactMarkdown>{markdown}</ReactMarkdown>
        </article>
    </div>
  );
};

export default Preview;
