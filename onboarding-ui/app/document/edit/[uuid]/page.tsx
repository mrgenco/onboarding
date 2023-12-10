'use client'
import React, { useState } from 'react';
import Markdown from './components/Markdown';
import Preview from './components/Preview';
import Link from 'next/link'

export default function Page({ params }: { params: { uuid: string} }) {
    const [markdown, setMarkdown] = useState('');


    return (
      <div className="flex">
        <Markdown onChange={setMarkdown} />
        <Preview markdown={markdown} />
      </div>
    );
}