'use client'
import React, { useState, ChangeEvent } from 'react';

interface MarkdownEditorProps {
  onChange: (markdown: string) => void;
}

const MarkdownEditor: React.FC<MarkdownEditorProps> = ({ onChange }) => {
  const [markdown, setMarkdown] = useState('');

  const handleInputChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    const newMarkdown = event.target.value;
    setMarkdown(newMarkdown);
    onChange(newMarkdown);
  };

  return (
    <div className="w-full h-screen p-4 border-l border-gray-300">

             
        <p className='text-blue-600'>Markdown Editor</p>
        <textarea
        className="w-full h-screen p-4"
        value={markdown}
        onChange={handleInputChange}
        placeholder="Enter your markdown here..."
        />
    </div>
  );
};

export default MarkdownEditor;