kubectl apply -f configs/creds-config.yaml
kubectl apply -f configs/postgres-config.yaml
kubectl apply -f volumes.yaml
kubectl apply -f deployments.yaml
kubectl apply -f services.yaml
sleep 1
kubectl apply -f ingress.yaml

clear
echo "Starting"
minikube dashboard