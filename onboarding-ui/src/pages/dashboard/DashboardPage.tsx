import React from 'react';
import { Grid, Card, CardContent, Typography, CardActionArea, Button } from '@mui/material';
import { getDocumentList } from './services/GetDocumentListService';
import { useNavigate } from 'react-router-dom';

interface ListItem {
  uuid: string;
  title: string;
  summary: string;
  createdBy: string;
  createdTime: string;
  lastUpdatedBy: string;
  lastUpdatedTime: string;
  status: number;
}

export default function DashboardPage() {
  const [listData, setListData] = React.useState<ListItem[]>([]);
  const [isLoading, setIsLoading] = React.useState<boolean>(false);
  const [error, setError] = React.useState<Error | null>(null);
  const navigate = useNavigate();

  React.useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      try {
        const response = await getDocumentList();
        setListData(response.data);
      } catch (error) {
        setError(error as Error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

  const handleStartReading = (uuid: string) => {
    navigate(`/document/${uuid}`);
  };


  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error fetching documents</div>;

  return (<Grid container spacing={3}>
    {listData.map((item: ListItem) => (
      <Grid key={item.uuid} item xs={12} sm={6} md={4} lg={3}>
        <Card>
          <CardContent>
            <Typography variant="h6" component="h2" gutterBottom>
              {item.title}
            </Typography>
            <Typography variant="body1" color="textPrimary" gutterBottom>
              {item.summary}
            </Typography>
            <Typography variant="body2" color="textSecondary">
              Created by: {item.createdBy}
            </Typography>
            <Typography variant="body2" color="textSecondary">
              Created Time: {item.createdTime}
            </Typography>
            <Typography variant="body2" color="textSecondary">
              Last Updated By: {item.lastUpdatedBy}
            </Typography>
            <Typography variant="body2" color="textSecondary">
              Last Updated Time: {item.lastUpdatedTime}
            </Typography>
            <Typography variant="body2" color="textSecondary">
              Status: {item.status}
            </Typography>
            <Button onClick={() => handleStartReading(item.uuid)} variant="outlined" color="primary" fullWidth>
              Start Reading
            </Button>
          </CardContent>
        </Card>
      </Grid>
    ))}
  </Grid>
  );
};