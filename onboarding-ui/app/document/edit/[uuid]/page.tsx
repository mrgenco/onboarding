'use client'
import React, { useState } from 'react';
import Markdown from './components/Markdown';
import Preview from './components/Preview';
import SaveDialog from './components/SaveDialog';
import Button from '../../../ui/button'

export default function Page({ params }: { params: { uuid: string} }) {
    const [markdown, setMarkdown] = useState('');
    const [saveContent, setSaveContent] = useState(false);

    return (
      <div className="flex">
        <Markdown onChange={setMarkdown} />
        <Preview markdown={markdown} />
        <Button title="Publish" style=""/>
        <Button title="Save as Draft" style=""/>
        <SaveDialog/>
      </div>
    );
}