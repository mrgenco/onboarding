'use client'
import React, { useState, ChangeEvent } from 'react';

interface MarkdownEditorProps {
  onChange: (markdown: string) => void;
}

const MarkdownEditor: React.FC<MarkdownEditorProps> = ({ onChange }) => {
  const [markdown, setMarkdown] = useState('');

  const handleInputChange = (e: ChangeEvent<HTMLTextAreaElement>) => {
    const newMarkdown = e.target.value;
    setMarkdown(newMarkdown);
    onChange(newMarkdown);
  };

  return (
    <textarea
      className="w-full h-screen p-4"
      value={markdown}
      onChange={handleInputChange}
      placeholder="Enter your markdown here..."
    />
  );
};

export default MarkdownEditor;