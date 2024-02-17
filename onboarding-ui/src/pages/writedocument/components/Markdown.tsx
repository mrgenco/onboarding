import TextField from '@mui/material/TextField';
import React, { useState, ChangeEvent } from 'react';

interface MarkdownEditorProps {
    onChange: (markdown: string) => void;
}

export default function MarkdownEditor(props: MarkdownEditorProps) {
    
    const [markdown, setMarkdown] = useState('');

    const handleInputChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
        const newMarkdown = event.target.value;
        setMarkdown(newMarkdown);
        props.onChange(newMarkdown);
    };

    return (
        <TextField
            id="filled-multiline-static"
            label="Write Your Document Here"
            multiline
            value={markdown}
            variant="filled"
            onChange={handleInputChange}
            fullWidth
            defaultValue="test"
        />
    );
};