import React from 'react';
import { renderDocument } from './RenderDocumentService';
import { Card, CardContent } from '@mui/material';
import ReactMarkdown from 'react-markdown';
import { useParams } from 'react-router-dom';

interface Document {
  title: string;
  lastUpdatedTime: string;
  content: string;
}

export default function ReadDocumentPage() {
  const [document, setDocument] = React.useState<Document | undefined>(undefined);
  const [errorMessage, setErrorMessage] = React.useState<string | null>(null);
  const { uuid } = useParams<{ uuid: string }>();

  React.useEffect(() => {
    const fetchData = async () => {
      if (!uuid) {
        setErrorMessage('Invalid document identifier.');
        return;
      }

      try {
        console.log('uuid :', uuid);
        const response = await renderDocument(uuid);
        setDocument(response.data);
        setErrorMessage(null);
      } catch (error) {
        setErrorMessage('Error fetching the document. Please try again later.');
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, [uuid]);

  return (
    <Card variant="outlined">
      <CardContent>
        {errorMessage ? (
          <p>{errorMessage}</p>
        ) : document ? (
          <ReactMarkdown>{document.content}</ReactMarkdown>
        ) : (
          <p>Loading document...</p>
        )}
      </CardContent>
    </Card>
  );
}
