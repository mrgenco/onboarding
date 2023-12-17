'use client'
import ReactMarkdown from 'react-markdown';

interface MarkdownPreviewProps {
  markdown: string;
}

export default function Preview(props: MarkdownPreviewProps) {
  return (
    <div className="w-full h-screen p-4 border-l border-gray-300">
        <p className='text-blue-600'>Preview</p>
        <article className="prose lg:prose-xl">
            <ReactMarkdown>{props.markdown}</ReactMarkdown>
        </article>
    </div>
  );
};

