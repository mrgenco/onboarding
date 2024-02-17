import Paper from '@mui/material/Paper';
import React from 'react';
import ReactMarkdown from 'react-markdown';

interface MarkdownPreviewProps {
    markdown: string;
}

export default function Preview(props: MarkdownPreviewProps) {
    return (


        <Paper> 
            <ReactMarkdown>{props.markdown}</ReactMarkdown> 
        </Paper>


    );
};
